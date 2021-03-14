import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Book} from '../model/book';
import {Observable} from 'rxjs';

const baseURL = 'http://localhost:8080/api';

@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor(
    private http: HttpClient
  ) { }

  getAll(): Observable<Book[]> {
    return this.http.get<Book[]>(`${baseURL}/books`);
  }

  findByName(name: any): Observable<Book[]> {
    return this.http.get<Book[]>(`${baseURL}/books?nameSearch=${name}`);
  }

  getBooksByCategory(id: any): Observable<Book[]> {
    return this.http.get<Book[]>(`${baseURL}/${id}/books`);
  }

  get(id: any): Observable<Book> {
    return this.http.get<Book>(`${baseURL}/books/${id}`);
  }

  create(data: any): Observable<any> {
    return this.http.post(`${baseURL}/books`, data);
  }

  update(data: any): Observable<any> {
    return this.http.put(`${baseURL}/books`, data);
  }

  delete(id: number): Observable<any> {
    return this.http.delete(`${baseURL}/books/${id}`);
  }
}
