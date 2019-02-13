import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable({
  providedIn: 'root'
})
export class PackagesService {

  //public API = '//localhost:8080';
  public API = '/server';
  public PACKAGE_API = this.API + '/packages';

  constructor(private http: HttpClient) { }
  getAll(): Observable<any> {
    return this.http.get('/server/packagesList');
  }

  get(id: string) {
    return this.http.get('/server/' + id);
  }

}
