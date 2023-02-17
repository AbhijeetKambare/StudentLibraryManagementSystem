package com.example.LibraryManagementSystem.DTOS;

public class getTransactionBCardDto {
    private int bookId;
    private int cardid;

    public getTransactionBCardDto() {
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getCardid() {
        return cardid;
    }

    public void setCardid(int cardid) {
        this.cardid = cardid;
    }
}
