import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BookHandlerClientService {

  http: string;
  constructor(
    private httpClient: HttpClient) {
      this.http = 'http://localhost:8080';
  }


  public getPublications(): Observable<Publication[]> {
    return this.httpClient.get<Publication[]>(this.http + '/publication/findAll');
  }
  public save(publication: Publication): Observable<Publication> {
    console.log('Post working');
    const options = this.createRequestOptions();
    return this.httpClient.post<Publication>(this.http + '/publication', publication);
  }
  private createRequestOptions(): HttpHeaders {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
    return headers;
  }
  public update(publication: Publication): Observable<Publication> {
    return this.httpClient.put<Publication>(this.http + '/publication', publication);
  }
  public getPublication(id: number): Observable<Publication>{
    return this.httpClient.get<Publication>(this.http + '/publication/' + id);
  }
  public delete(id: number): Observable<Publication>{
    return this.httpClient.delete<Publication>(this.http + '/publication/' + id);
  }
}

export interface Genre {
  name: string;
}

export class Publication {
  id: number;
  title: string;
  author: string;
  releaseYear: number;
  genre: Genre[];
  dateAdded: Date;
  photoUrls: string;
}
