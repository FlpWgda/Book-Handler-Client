import {Component, Inject, OnInit} from '@angular/core';
import {BookHandlerClientService, Genre, Publication} from '../../services/book-handler-client.service';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';


export interface DialogData {
  animal: string;
  name: string;
}

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  publications: Publication[];

  publication: Publication;

  constructor(private bookHandlerClientService: BookHandlerClientService, public dialog: MatDialog) {
  }

  ngOnInit(): void {
    console.log('hello world');
    this.bookHandlerClientService.getPublications().subscribe(value => {
      this.publications = value;
    });
  }
  openDialog(id?: number): void{
    if (id) {
      this.publications.forEach(publication => {
        if (publication.id === id){
          this.publication = publication;
        }
      });
    }
    else{
      this.publication = new Publication();
    }
    const dialogRef = this.dialog.open(DialogOverviewExampleDialog, {
      width: '250px',
      data: this.publication
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log(result);
      this.bookHandlerClientService.save(result).subscribe(publication => this.ngOnInit());
    });
  }
  delete(id: number): void{
    this.bookHandlerClientService.delete(id).subscribe(publication => this.ngOnInit());
  }
}
@Component({
  // tslint:disable-next-line:component-selector
  selector: 'dialog-overview-example-dialog',
  templateUrl: 'dialog-overview-example-dialog.html',
})
// tslint:disable-next-line:component-class-suffix
export class DialogOverviewExampleDialog {

  constructor(
    public dialogRef: MatDialogRef<DialogOverviewExampleDialog>,
    @Inject(MAT_DIALOG_DATA) public data: Publication) {
  }

  onNoClick(): void {
    this.dialogRef.close();
  }
  onSubmit(): void {
    console.log(this.data);
    this.dialogRef.close();
  }

}
