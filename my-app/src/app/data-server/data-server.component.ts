import { Component } from '@angular/core';

@Component({
  selector: 'app-data-server',
  templateUrl: './data-server.component.html',
  styleUrls: ['./data-server.component.css']
})
export class DataServerComponent {

  listData: string[] = ["yamaha", "suzuki", "Honda"];
  habilitar: boolean = false;
  constructor() { }

  setHabilitar(): void {
    this.habilitar = (this.habilitar) ? false : true;
  }
  getHabilitar(): string {
    return this.habilitar ? "Ocultar" : "Habilitar";
  }
}
