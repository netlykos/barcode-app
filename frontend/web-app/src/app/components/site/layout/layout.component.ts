import { Component, Input, OnInit, Output, ViewChild } from '@angular/core'
import { MatSidenav } from '@angular/material/sidenav';
import { Router } from '@angular/router';
import { MenuOption } from 'src/app/models/menu-option';
import { menuOptions } from '../../../models/menu-options'

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss']
})
export class LayoutComponent implements OnInit {

  @Output() pageTitle: string = 'Barcode App';
  @Output() menuOptions: MenuOption[] = menuOptions;
  @ViewChild('sidenav') sideNav!: MatSidenav;

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  selectMenuOption (menuOption: MenuOption): void {
    console.log(menuOption);
    this.router.navigate([menuOption.route]).then( v => {
      this.sideNav.toggle();
      this.pageTitle = menuOption.displayName;
    });
  }

}
