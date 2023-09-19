import {ChangeDetectionStrategy, Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {KeyResult} from '../shared/types/model/KeyResult';
import {DATE_FORMAT} from "../shared/constantLibary";

@Component({
  selector: 'app-keyresult-detail',
  templateUrl: './keyresult-detail.component.html',
  styleUrls: ['./keyresult-detail.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class KeyresultDetailComponent implements OnInit {
  @Input() keyResult!: KeyResult;
  constructor() {}
  @Output() close: EventEmitter<any> = new EventEmitter<any>();
  closeDrawer() {
    this.close.emit()
  }

  ngOnInit(): void {}
}
