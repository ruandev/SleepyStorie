package dev.ruanvictor.sleepystorie.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import dev.ruanvictor.sleepystorie.data.model.Book;
import dev.ruanvictor.sleepystorie.data.repository.BookRepository;

public class BooksViewModel extends ViewModel {
    private BookRepository bookRepository = new BookRepository();
    private MutableLiveData<List<Book>> mBooks = null;

    public LiveData<List<Book>> getBooks() {
        if (mBooks == null) {
            mBooks = new MutableLiveData<>();
            List<Book> tmp = bookRepository.getAllBooks();
            mBooks.postValue(tmp);
        }
        return mBooks;
    }
}
