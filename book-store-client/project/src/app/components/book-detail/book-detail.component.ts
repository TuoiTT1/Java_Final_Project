import { Component, OnInit } from '@angular/core';
import {Book} from '../../model/book';
import {BookService} from '../../service/book.service';
import {ActivatedRoute} from '@angular/router';
import {Category} from '../../model/category';
import {CategoryService} from '../../service/category.service';

@Component({
  selector: 'app-book-detail',
  templateUrl: './book-detail.component.html',
  styleUrls: ['./book-detail.component.css']
})
export class BookDetailComponent implements OnInit {
  book: Book = {
    id: 0,
    name: '',
    author: '',
    yearOfPublisher: 0,
    publisher: '',
    quantity: 0,
    price: 0,
    categoryId: 0
  };
  category: Category = {
    id: 0,
    title: ''
  };
  constructor(
    private bookService: BookService,
    private categoryService: CategoryService,
    private route: ActivatedRoute,
  ) {
  }

  ngOnInit(): void {
    this.getBookById(this.route.snapshot.params.id);
  }

  getBookById(id: any): void {
    this.bookService.get(id)
      .subscribe(data => {
          this.book = data;
          this.categoryService.get(data.categoryId).subscribe(
            data1 => {
              this.category = data1;
            } ,
            error => {
              console.log('error when get category by id: ' + error);
            }
          );
        },
        error => {
          console.log('error when get book by id: ' + error);
        });
  }
  previousState(): void {
    window.history.back();
  }
}
