import { Injectable } from '@angular/core';
import { Vmotor } from './vmotor';
import { MOTOR } from './vmoto.json';
import { Observable, of } from 'rxjs';

@Injectable()//SERVICES
export class VmotoService {

  constructor() { }

  getVMoto(): Observable<Vmotor[]> {
    return of(MOTOR);
  }
}
