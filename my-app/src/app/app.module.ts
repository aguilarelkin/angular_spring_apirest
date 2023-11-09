import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { NavComponent } from './nav/nav.component';
import { FooterComponent } from './footer/footer.component';
import { DataServerComponent } from './data-server/data-server.component';
import { VmotoComponent } from './vmoto/vmoto.component';
import { VmotoService } from './vmoto/vmoto.service';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';
import { FormComponent } from './vmoto/form.component';
import { FormsModule } from '@angular/forms'

const routes: Routes = [
  { path: '', redirectTo: '/vmoto', pathMatch: 'full' },
  { path: 'vmoto', component: VmotoComponent },
  { path: 'data', component: DataServerComponent },
  { path: 'create', component: FormComponent },
  { path: 'create/:id', component: FormComponent }



];

@NgModule({
  declarations: [//component
    AppComponent,
    NavComponent,
    FooterComponent,
    DataServerComponent,
    VmotoComponent,
    FormComponent

  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    RouterModule.forRoot(routes)
  ],
  //service
  providers: [VmotoService],
  bootstrap: [AppComponent]
})
export class AppModule { }
