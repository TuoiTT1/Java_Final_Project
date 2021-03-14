import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {BookListComponent} from './components/book-list/book-list.component';
import {BookDetailComponent} from './components/book-detail/book-detail.component';
import {AddBookComponent} from './components/add-book/add-book.component';
import {MainComponent} from './main/main.component';

const routes: Routes = [{
  path: '',
  component: MainComponent,
  children: [
    {path: '', redirectTo: 'books', pathMatch: 'full'},
    {path: 'books', component: BookListComponent},
    {path: 'books/:id', component: BookDetailComponent},
    {path: 'add', component: AddBookComponent},
    {path: 'edit/:id', component: AddBookComponent},
    {path: ':id/books', component: BookListComponent}
  ]
}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
