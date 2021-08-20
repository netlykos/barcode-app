import { Component, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.scss']
})
export class LayoutComponent implements OnInit {

  @Output() pageTitle: string = 'Barcode App';

  constructor() { }

  ngOnInit(): void {
  }

}
