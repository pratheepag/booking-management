import { Component, OnInit } from '@angular/core';
import { PackagesService } from '../shared/packages/packages.service';


@Component({
  selector: 'app-package-list',
  templateUrl: './package-list.component.html',
  styleUrls: ['./package-list.component.css']
})
export class PackageListComponent implements OnInit {
  packages: Array<any>;
  constructor(private packagesService: PackagesService) { }

  ngOnInit() {
    this.packagesService.getAll().subscribe(data => {
      this.packages = data;
    });
  }

}
