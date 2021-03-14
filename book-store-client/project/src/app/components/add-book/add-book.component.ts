import {Component, OnInit} from '@angular/core';
import {Book} from '../../model/book';
import {BookService} from '../../service/book.service';
import {ActivatedRoute} from '@angular/router';
import {HttpErrorResponse, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Category} from '../../model/category';
import {CategoryService} from '../../service/category.service';

@Component({
  selector: 'app-add-book',
  templateUrl: './add-book.component.html',
  styleUrls: ['./add-book.component.css']
})
export class AddBookComponent implements OnInit {
  isSaving: boolean | undefined;
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
  categories: Category[] | undefined;
  idBookEdit?: number;

  constructor(
    private bookService: BookService,
    private categoryService: CategoryService,
    private route: ActivatedRoute) {
  }

  ngOnInit(): void {
    this.isSaving = false;
    // this.activatedRoute.data.subscribe(({book}) => {
    //   this.book = book;
    // });
    this.categoryService.getAll()
      .subscribe(data => {
          this.categories = data;
          console.log(data);
          console.log(this.categories);
        },
        error => {
          console.log(error);
        });
    this.idBookEdit = this.route.snapshot.params.id;
    if (this.idBookEdit !== undefined && this.idBookEdit !== null) {
      this.bookService.get(this.idBookEdit)
        .subscribe(data => {
            this.book = data;
       },
          error => {
            console.log('error when edit book has id ' + this.idBookEdit + ': ' + error);
          });
    }
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    if (this.book.id !== 0) {
      this.subscribeToSaveResponse(this.bookService.update(this.book));
    } else {
      this.subscribeToSaveResponse(this.bookService.create(this.book));
    }
  }

  trackCategoryById(index: number, item: Category): any {
    return item.id;
  }

  private subscribeToSaveResponse(result: Observable<HttpResponse<Book>>): void {
    result.subscribe(
      (res: HttpResponse<Book>) => {
        this.onSaveSuccess();
      },
      (res: HttpErrorResponse) => {
        this.onSaveError();
      }
    );
  }

  private onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  private onSaveError(): void {
    this.isSaving = false;
  }
}
