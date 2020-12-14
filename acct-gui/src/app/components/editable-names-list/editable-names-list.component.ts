import { Component, OnInit, Input, Output, EventEmitter, SimpleChanges, OnChanges } from '@angular/core';
import { StateService } from 'src/app/services/state-service/state.service';
import { ThrowStmt } from '@angular/compiler';

@Component({
  selector: 'app-editable-names-list',
  templateUrl: './editable-names-list.component.html',
  styleUrls: ['./editable-names-list.component.css']
})
export class EditableNamesListComponent implements OnInit, OnChanges {
    @Input() listItems : any[];

    @Input() displayValueCallback : Function;

    @Input() newItemCallback : Function;

    @Output() onSelect: EventEmitter<any> = new EventEmitter();

    @Output() onAdd: EventEmitter<any> = new EventEmitter();

    @Output() onDelete: EventEmitter<any> = new EventEmitter();

    @Output() onEditBegin: EventEmitter<any> = new EventEmitter();

    @Output() onEditEnd: EventEmitter<any> = new EventEmitter();

    private selectedItem;

    private selectedItemDisplatValue : string;

    constructor(state : StateService) { }

    ngOnInit() {
    }

    ngOnChanges(changes: SimpleChanges) {
        this.selectedItem = null;
        this.selectedItemDisplatValue = null;
    }

    private selectItem(item : any) {
        this.selectedItem = item;
        this.onSelect.emit(item);
    }

    private addButtonClicked() {
        this.selectedItem = this.newItemCallback();
        this.listItems.push(this.selectedItem);
        this.selectedItemDisplatValue = this.displayValueCallback(this.selectedItem);
        this.onAdd.emit(this.selectedItem);
    }

    private editButtonClicked() {
        this.selectedItemDisplatValue = this.displayValueCallback(this.selectedItem);
        this.onEditBegin.emit(this.selectedItem);
    }

    private deleteButtonClicked() {
        this.onDelete.emit(this.selectedItem);
    }

    private onEditInputKeyUp(event) {
        if (event.key == "Enter") {
            this.onEditEnd.emit(this.selectedItemDisplatValue);
            this.selectedItemDisplatValue = null;
        }
    }
}
