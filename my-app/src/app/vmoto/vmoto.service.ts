import { Injectable } from '@angular/core';
import { Vmotor } from './vmotor';
import { MOTOR } from './vmoto.json';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable()//SERVICES
export class VmotoService {

  private urlEndPoint: string = "http://localhost:8080/api/v1/moto";

  private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' })


  constructor(private http: HttpClient) { }

  getVMoto(): Observable<Vmotor[]> {
    //return of(MOTOR);

    return this.http.get<Vmotor[]>(this.urlEndPoint)
  }

  create(vmoto: Vmotor): Observable<Vmotor> {
    return this.http.post<Vmotor>(this.urlEndPoint, vmoto, { headers: this.httpHeaders })
  }

  getVmotoId(id: number): Observable<Vmotor> {
    return this.http.get<Vmotor>(`${this.urlEndPoint}/${id}`)
  }

  update(vmoto: Vmotor): Observable<Vmotor> {
    return this.http.put<Vmotor>(`${this.urlEndPoint}/${vmoto.id}`, vmoto, { headers: this.httpHeaders })
  }
  delete(id: number): Observable<Vmotor> {
    return this.http.delete<Vmotor>(`${this.urlEndPoint}/ ${id}`, { headers: this.httpHeaders })
  }
}
