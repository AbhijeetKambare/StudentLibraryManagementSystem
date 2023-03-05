package com.example.LibraryManagementSystem.DTOS;

public class returnBookTransactionDto {
    private int bookId;
    private int cardId;

    public returnBookTransactionDto() {
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }
}
