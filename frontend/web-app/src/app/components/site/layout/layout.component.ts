import { Component, OnInit, Output, ViewChild } from '@angular/core'
import { MatSidenav } from '@angular/material/sidenav';
import { Router, Route } from '@angular/router';
import { MenuOption } from 'src/app/models/menu-option';
import { menuOptions } from '../../../models/menu-options'
import { NavRoute } from '../../../models/nav-route'

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss']
})
export class LayoutComponent implements OnInit {

  @Output() pageTitle: string = 'Barcode App';
  @Output() menu: NavRoute[] = [];
  @Output() menuOptions: MenuOption[] = menuOptions;
  @ViewChild('sidenav') sideNav!: MatSidenav;

  constructor(private router: Router) {
  }

  ngOnInit(): void {
    this.printPath('', this.router.config);
  }

  selectMenuOption(menuOption: MenuOption): void {
    console.log(menuOption);
    this.router.navigate([menuOption.route]).then(v => {
      this.sideNav.toggle();
      this.pageTitle = menuOption.displayName;
    });
  }

  printPath(parent: string, config: Route[]) {
    for (let route of config) {
      if (route.data) {
        const navRoute = route.data as NavRoute;
        this.menu.push(navRoute);
      }
      if (route.children) {
        const currentPath = route.path ? parent + '/' + route.path : parent;
        this.printPath(currentPath, route.children);
      }
    }
  }

}
