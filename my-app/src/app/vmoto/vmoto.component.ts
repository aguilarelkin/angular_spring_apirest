import { Component, OnInit } from '@angular/core';
import { Vmotor } from './vmotor';
import { VmotoService } from './vmoto.service';
import { tap } from 'rxjs';

@Component({
  selector: 'app-vmoto',
  templateUrl: './vmoto.component.html',
  styleUrls: ['./vmoto.component.css']
})
export class VmotoComponent implements OnInit {

  motos: Vmotor[] = [];

  constructor(private vmotoService: VmotoService) { }

  ngOnInit(): void {
    /* this.motos = */ this.vmotoService.getVMoto().pipe(
    tap(vmoto => {
      //operation puede hacer la tarea de subsicribe
    })
  ).subscribe(
    (vmoto) => this.motos = vmoto
  );
  }

  delete(vmoto: Vmotor): void {
    this.motos = this.motos.filter(vm => vm !== vmoto)

    this.vmotoService.delete(vmoto.id).subscribe(
      respo => alert(`Moto eliminada ${vmoto.name}`)
    )
  }
}
