import { Component, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { NavItem } from '../models/nav-items';
import { MediaChange, MediaObserver } from "@angular/flex-layout";
import { Subscription } from 'rxjs';
import { menu } from '../models/menu';
import { Title } from '@angular/platform-browser';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { filter, map } from 'rxjs/operators';
import { MatSidenav } from '@angular/material/sidenav';

@Component({
  selector: 'app-material-layout',
  templateUrl: './material-layout.component.html',
  styleUrls: ['./material-layout.component.scss']
})
export class MaterialLayoutComponent implements OnDestroy, OnInit {

  private mediaWatcher: Subscription;
  opened: boolean = true;
  menu: NavItem[] = menu;
  private defaultTitle: string = 'Barcode App';
  @Output() title: string = this.defaultTitle;

  constructor(private media: MediaObserver, private titleService: Title, private router: Router, private activatedRoute: ActivatedRoute) {
    this.mediaWatcher = this.media.media$.subscribe((mediaChange: MediaChange) => {
      this.handleMediaChange(mediaChange)
    })
  }

  private handleMediaChange(mediaChange: MediaChange) {
    if (this.media.isActive('lt-md')) {
      this.opened = false
    } else {
      this.opened = true
    }
  }

  ngOnInit(): void {
    const appTitle = this.defaultTitle;
    this.router.events.pipe(
      filter(event => event instanceof NavigationEnd),
    ).subscribe(() => {
      var rt = this.getChild(this.activatedRoute)
      rt.data.subscribe((data: { title: string; }) => {
        this.title = data.title
        console.log(data.title)
      })
    })
  }

  ngOnDestroy() {
    this.mediaWatcher.unsubscribe();
  }

  getChild(activatedRoute: ActivatedRoute): any {
    if (activatedRoute.firstChild) {
      return this.getChild(activatedRoute.firstChild);
    }
    return activatedRoute;
  }

}
