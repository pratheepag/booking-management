import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs/Subscription';
import { ActivatedRoute, Router } from '@angular/router';
import { PackagesService } from '../shared/packages/packages.service';

@Component({
  selector: 'app-package-view',
  templateUrl: './package-view.component.html',
  styleUrls: ['./package-view.component.css']
})
export class PackageViewComponent implements OnInit {

  packages: any = {};

  sub: Subscription;
  constructor(private route: ActivatedRoute,
    private router: Router,
    private packagesService: PackagesService,) { }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      const id = params['id'];
      if (id) {
        this.packagesService.get(id).subscribe((packages: any) => {
          if (packages) {
            this.packages = packages;
            this.packages.href = packages._links.self.href;
            
          } else {
            console.log(`Packages with id '${id}' not found, returning to list`);
            this.gotoList();
          }
        });
      }
    });
  }

  gotoList() {
    this.router.navigate(['/package-list']);
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

}
