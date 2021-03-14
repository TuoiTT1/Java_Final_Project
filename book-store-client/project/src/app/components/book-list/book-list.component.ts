import {Component, OnInit} from '@angular/core';
import {Book} from '../../model/book';
import {BookService} from '../../service/book.service';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.css']
})
export class BookListComponent implements OnInit {
  books?: Book[];
  idCategory?: any;
  nameSearch = '';

  constructor(
    private bookService: BookService,
    private route: ActivatedRoute
  ) {
  }

  ngOnInit(): void {
    this.retrieveBook();
  }

  retrieveBook(): void {
    this.idCategory = this.route.snapshot.params.id;
    if (this.idCategory === undefined) {
      this.bookService.getAll()
        .subscribe(data => {
            this.books = data;
            console.log(data);
            console.log(this.books);
          },
          error => {
            console.log(error);
          });
    } else {
      this.bookService.getBooksByCategory(this.idCategory)
        .subscribe(data => {
            this.books = data;
            console.log(data);
            console.log(this.books);
          },
          error => {
            console.log(error);
          });
    }
  }
  refreshList(): void {
    this.retrieveBook();
   }
  searchBookLike(): void {
    this.bookService.findByName(this.nameSearch)
      .subscribe(data => {
          this.books = data;
          console.log(data);
          console.log(this.books);
        },
        error => {
          console.log(error);
        });
  }
}
