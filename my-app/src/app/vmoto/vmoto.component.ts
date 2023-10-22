import { Component, OnInit } from '@angular/core';
import { Vmotor } from './vmotor';
import { VmotoService } from './vmoto.service';

@Component({
  selector: 'app-vmoto',
  templateUrl: './vmoto.component.html',
  styleUrls: ['./vmoto.component.css']
})
export class VmotoComponent implements OnInit {

  motos: Vmotor[] = [];

  constructor(private vmotoService: VmotoService) { }

  ngOnInit(): void {
    /* this.motos = */ this.vmotoService.getVMoto().subscribe(
    (vmoto) => this.motos = vmoto
  );
  }

}
