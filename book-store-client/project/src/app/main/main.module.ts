import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {BookListComponent} from '../components/book-list/book-list.component';
import {BookDetailComponent} from '../components/book-detail/book-detail.component';
import {AddBookComponent} from '../components/add-book/add-book.component';
import {MainComponent} from './main.component';
import {RouterModule} from '@angular/router';
import {FlexLayoutModule} from '@angular/flex-layout';
import {SharedModule} from '../layout/shared.module';
import {FormsModule} from '@angular/forms';


@NgModule({
  declarations: [
    MainComponent,
    BookListComponent,
    BookDetailComponent,
    AddBookComponent
  ],
  imports: [
    CommonModule,
    RouterModule,
    FlexLayoutModule,
    SharedModule,
    FormsModule
  ]
})
export class MainModule {
}
