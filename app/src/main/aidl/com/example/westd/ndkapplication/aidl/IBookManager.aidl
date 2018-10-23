// IBookManager.aidl
package com.example.westd.ndkapplication.aidl;

// Declare any non-default types here with import statements
import com.example.westd.ndkapplication.aidl.Book;

interface IBookManager {

    void addBook(in Book boo);

    List<Book> getBookList();

}
