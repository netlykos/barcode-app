import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialLayoutComponent } from './material-layout/material-layout.component';
import { BarcodeComponent } from './components/barcode/barcode.component';
import { HomeComponent } from './components/home/home.component';
import { UtilitiesComponent } from './components/utilities/utilities.component';
import { MaterialModule } from './material.module';

@NgModule({
  declarations: [
    AppComponent,
    MaterialLayoutComponent,
    BarcodeComponent,
    HomeComponent,
    UtilitiesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
