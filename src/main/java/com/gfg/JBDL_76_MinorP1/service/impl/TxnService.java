package com.gfg.JBDL_76_MinorP1.service.impl;

import com.gfg.JBDL_76_MinorP1.dto.request.TxnRequest;
import com.gfg.JBDL_76_MinorP1.dto.request.TxnreturnRequest;
import com.gfg.JBDL_76_MinorP1.enums.TxnStatus;
import com.gfg.JBDL_76_MinorP1.exception.BookException;
import com.gfg.JBDL_76_MinorP1.exception.TxnException;
import com.gfg.JBDL_76_MinorP1.exception.UserException;
import com.gfg.JBDL_76_MinorP1.model.Book;
import com.gfg.JBDL_76_MinorP1.model.Txn;
import com.gfg.JBDL_76_MinorP1.model.User;
import com.gfg.JBDL_76_MinorP1.repository.TxnRepository;
//import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TxnService {

    @Autowired
    private TxnRepository txnRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Value("${user.valid.days}")
    private int validUpto;

    @Value("${user.delayed.finePerDay}")
    private int finePerDay;

    @Transactional(rollbackFor = {BookException.class, UserException.class})
    public String create(TxnRequest txnRequest) throws UserException, BookException {
        //user is trying to get the book, check user is valid or not?
        User userFromDb = userService.checkIfUserIsValid(txnRequest.getUserEmail());
        if(userFromDb == null) {
            throw new UserException("User is not valid");
        }

        //book no he is asking, check actually it belongs to my library
        Book bookFromDb = bookService.checkIfBookIsValid(txnRequest.getBookNo());
        if(bookFromDb == null) {
            throw new BookException("book is not valid");
        }

        //book a user is asking should not be assigned to some another user?
        if(bookFromDb.getUser() != null) {
            throw new BookException("book is not free to be issued");
        }
        return createTxn(userFromDb, bookFromDb);
    }

    @Transactional
    public String createTxn(User userFromDb, Book bookFromDb) throws BookException {
        //create a txn
        String txnId = UUID.randomUUID().toString();
        Txn txn = Txn.
                builder().
                txnId(txnId).
                user(userFromDb).
                book(bookFromDb).
                txnStatus(TxnStatus.ISSUED).
                issuedDate(new Date()).
                build();
        txnRepository.save(txn);
        bookService.markBookUnavailable(bookFromDb, userFromDb);
        return txnId;
    }

    // @Transactional-this required when we r making any query in the DBase
    @Transactional(rollbackFor = {BookException.class, UserException.class})
    public Double returnTxn(TxnreturnRequest txnreturnRequest) throws UserException, BookException {
        //user is valid or not
        User userFromDb = userService.checkIfUserIsValid(txnreturnRequest.getUserEmail());
        if(userFromDb == null) {
            throw new UserException("User is not valid");
        }

        //book no he is asking, check actually it belongs to my library
        Book bookFromDb = bookService.checkIfBookIsValid(txnreturnRequest.getBookNo());
        if(bookFromDb == null) {
            throw new BookException("book is not valid");
        }

        if(bookFromDb.getUser() != null && bookFromDb.getUser().equals(userFromDb)) {
           Txn txnFromDb = txnRepository.findByTxnId(txnreturnRequest.getTxnId());
           if(txnFromDb == null) {
               throw new TxnException("No txn has been found in my db with this txnid");
           }

           Double amount = calculateSettlementAmount(txnFromDb, bookFromDb);
           if(amount == bookFromDb.getSecurityAmount())
               txnFromDb.setTxnStatus((TxnStatus.RETURNED));
           else
               txnFromDb.setTxnStatus(TxnStatus.FINED);
           txnFromDb.setSettlementAmount(amount);
           //mark the book as available
            bookFromDb.setUser(null);
            txnRepository.save(txnFromDb);
            return amount;
        } else{
              throw new TxnException("Book is assigned to someone else, or not at all assigned"); //exception is RuntimeException so do not need to mention
        }

    }

    public Double calculateSettlementAmount(Txn txnFromDb, Book bookFromDb) {
        long issueTime = txnFromDb.getIssuedDate().getTime();
        long returnTime = System.currentTimeMillis();
        long diff = returnTime - issueTime;
        int daysPassed = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        if(daysPassed > validUpto) {
            int fineAmount = (daysPassed - validUpto)*finePerDay;
            return bookFromDb.getSecurityAmount()-fineAmount;
        }
        return bookFromDb.getSecurityAmount();
    }
}
