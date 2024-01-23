import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LayoutModule } from './feature-modules/layout/layout.module';
import { HttpClientModule } from '@angular/common/http';
import { ProfilesModule } from './feature-modules/profiles/profiles.module';
import { OrdersModule } from './feature-modules/orders/orders.module';
import {MatNativeDateModule} from '@angular/material/core';
import {DatePipe} from "@angular/common";
import { MapComponent } from './infrastructure/map/map.component';


@NgModule({
  declarations: [
    AppComponent,
    MapComponent,
  ],
  imports: [
    AppRoutingModule,
    LayoutModule,
    HttpClientModule,
    LayoutModule,
    ProfilesModule,
    OrdersModule,
    MatNativeDateModule
  ],
  providers: [DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
