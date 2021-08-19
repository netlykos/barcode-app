import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BarcodeComponent } from './components/barcode/barcode.component';
import { HomeComponent } from './components/home/home.component';
import { UtilitiesComponent } from './components/utilities/utilities.component';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent, data: { title: 'Barcode App' } },
  { path: 'barcode', component: BarcodeComponent, data: { title: 'Barcode App: Generate' } },
  { path: 'utilities', component: UtilitiesComponent, data: { title: 'Barcode App: Utilities' } },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
