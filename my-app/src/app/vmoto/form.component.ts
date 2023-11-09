import { Component, OnInit } from '@angular/core';
import { Vmotor } from './vmotor';
import { VmotoService } from './vmoto.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html'
})
export class FormComponent implements OnInit {

  protected vmoto: Vmotor = new Vmotor()
  protected title: string = "VMOTORS CREATE"
  protected errores: string[] = [];

  constructor(private vmotoService: VmotoService, private router: Router, private activatedRoute: ActivatedRoute) {

  }

  ngOnInit(): void {
    this.getVmoto()
  }
  getVmoto(): void {
    this.activatedRoute.params.subscribe(params => {
      let id = params['id'];
      if (id) {
        this.vmotoService.getVmotoId(id).subscribe(
          vmotos => this.vmoto = vmotos
        )
      }
    })
  }
  create(): void {
    this.vmotoService.create(this.vmoto).subscribe(
      vmoto => {
        this.router.navigate(["/vmoto"])
        alert(`El moto ${vmoto.name} ha sido creado con éxito`)
      }, 
      err =>{
        this.errores = err.error.errors as string[];
      }
    )
  }
  update(): void {
    this.vmotoService.update(this.vmoto).subscribe(
      json => {
        this.router.navigate(["/vmoto"])
        alert(`${json.response} : ${json.vmoto.name}`)
      }, 
      err =>{
        this.errores = err.error.errors as string[];
      }
    )
  }


}
