import { NgModule } from '@angular/core';
import { BrowserModule, Title } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialLayoutComponent } from './material-layout/material-layout.component';
import { BarcodeComponent } from './components/barcode/barcode.component';
import { HomeComponent } from './components/home/home.component';
import { UtilitiesComponent } from './components/utilities/utilities.component';
import { MaterialModule } from './material.module';
import { MenuListItemComponent } from './components/menu-list-item/menu-list-item.component'
import { LayoutComponent } from './components/site/layout/layout.component'

@NgModule({
  declarations: [
    AppComponent,
    MaterialLayoutComponent,
    LayoutComponent,
    BarcodeComponent,
    HomeComponent,
    UtilitiesComponent,
    MenuListItemComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
  ],
  providers: [
    Title,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
