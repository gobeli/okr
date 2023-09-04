import { Component, Input, OnInit } from '@angular/core';
import { KeyresultMin } from '../shared/types/model/KeyresultMin';

@Component({
  selector: 'app-keyresult',
  templateUrl: './keyresult.component.html',
  styleUrls: ['./keyresult.component.scss'],
})
export class KeyresultComponent implements OnInit {
  @Input() keyResult!: KeyresultMin;

  constructor() {}

  ngOnInit(): void {}
}
