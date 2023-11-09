import { Injectable } from '@angular/core';
import { Vmotor } from './vmotor';
import { MOTOR } from './vmoto.json';
import { Observable, catchError, map, of, tap, throwError } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { DatePipe } from '@angular/common';

@Injectable()//SERVICES
export class VmotoService {

  private urlEndPoint: string = "http://localhost:8080/api/v1/moto";

  private httpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' })


  constructor(private http: HttpClient, private router: Router) { }

  getVMoto(): Observable<Vmotor[]> {
    //return of(MOTOR);

    return this.http.get(this.urlEndPoint).pipe(
      tap(response => {
        let moto = response as Vmotor[];
        moto.forEach(
          c => {
            console.log(c.name)
          }
        )
      }),

      map(response => {
        let moto = response as Vmotor[];
        return moto.map(motos => {
          motos.name = motos.name.toUpperCase()
          let datePipe = new DatePipe('en-US');

          return motos
        })
      }),
      catchError(e => {
        alert(`Error, VUELVE MÁS TARDE :( ${e.error.response} )`);
        return throwError(e)
      })
    )
  }

  create(vmoto: Vmotor): Observable<Vmotor> {
    return this.http.post(this.urlEndPoint, vmoto, { headers: this.httpHeaders }).pipe(
      map((response: any) => response.vmoto as Vmotor),
      catchError(e => {
        if (e.status == 400) {
          return throwError(e);
        }
        alert(`Error al crear el moto.  ${e.error.response}`);
        return throwError(e)
      })
    )
  }

  getVmotoId(id: number): Observable<Vmotor> {
    return this.http.get<Vmotor>(`${this.urlEndPoint}/${id}`).pipe(
      catchError(e => {
        if (e.status == 400) {
          return throwError(e);
        }
        this.router.navigate(['/vmoto']);
        alert(`Error ${e.error.response}`);
        return throwError(e)
      })
    )
  }

  update(vmoto: Vmotor): Observable<any> {
    return this.http.put<any>(`${this.urlEndPoint}/${vmoto.id}`, vmoto, { headers: this.httpHeaders }).pipe(
      catchError(e => {
        alert(`Error al editar el moto.  ${e.error.response}`);
        return throwError(e)
      })
    )
  }
  delete(id: number): Observable<Vmotor> {
    return this.http.delete<Vmotor>(`${this.urlEndPoint}/ ${id}`, { headers: this.httpHeaders }).pipe(
      catchError(e => {
        alert(`Error al eliminar el moto.  ${e.error.response}`);
        return throwError(e)
      })
    )
  }
}
