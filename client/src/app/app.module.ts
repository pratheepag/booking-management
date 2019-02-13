import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { PackagesService } from './shared/packages/packages.service';
import {HttpClientModule} from '@angular/common/http';
import { MatButtonModule, MatCardModule, MatInputModule, MatListModule, MatToolbarModule } from '@angular/material';
import { AppComponent } from './app.component';
import { PackageListComponent } from './package-list/package-list.component';
import { PackageViewComponent } from './package-view/package-view.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule, Routes } from '@angular/router';

const appRoutes: Routes = [
  { path: '', redirectTo: '/package-list', pathMatch: 'full' },
  {
    path: 'package-list',
    component: PackageListComponent
  },
  {
    path: 'package-view/:id',
    component: PackageViewComponent
  }
];
@NgModule({
  declarations: [
    AppComponent,
    PackageListComponent,
    PackageViewComponent

  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    MatListModule,
    MatToolbarModule,
    RouterModule.forRoot(appRoutes)

  ],
  providers: [PackagesService],
  bootstrap: [AppComponent]
})
export class AppModule { }
