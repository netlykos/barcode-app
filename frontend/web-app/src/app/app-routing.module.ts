import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BarcodeComponent } from './components/barcode/barcode.component';
import { HomeComponent } from './components/home/home.component';
import { UtilitiesComponent } from './components/utilities/utilities.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'home', component: HomeComponent },
  { path: 'barcode', component: BarcodeComponent },
  { path: 'utilities', component: UtilitiesComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
