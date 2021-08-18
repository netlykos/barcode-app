import { Component, OnDestroy } from '@angular/core';
import { NavItem } from '../models/nav-items';
import { MediaChange, MediaObserver } from "@angular/flex-layout";
import { Subscription } from 'rxjs';
import { menu } from '../models/menu';

@Component({
  selector: 'app-material-layout',
  templateUrl: './material-layout.component.html',
  styleUrls: ['./material-layout.component.scss']
})
export class MaterialLayoutComponent implements OnDestroy {

  opened: boolean = true;
  private mediaWatcher: Subscription;
  menu: NavItem[] = menu;

  constructor(private media: MediaObserver) {
      this.mediaWatcher = this.media.media$.subscribe((mediaChange: MediaChange) => {
          this.handleMediaChange(mediaChange);
      })
  }

  private handleMediaChange(mediaChange: MediaChange) {
      if (this.media.isActive('lt-md')) {
          this.opened = false;
      } else {
          this.opened = true;
      }
  }

  ngOnDestroy() {
      this.mediaWatcher.unsubscribe();
  }

}