import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LayoutModule } from './feature-modules/layout/layout.module';
import { HttpClientModule } from '@angular/common/http';
import { ProfilesModule } from './feature-modules/profiles/profiles.module';
import { SimulatorsModule } from './feature-modules/simulators/simulators.module';

@NgModule({
  declarations: [
    AppComponent,
  ],
  imports: [
    AppRoutingModule,
    LayoutModule,
    HttpClientModule,
    LayoutModule,
    ProfilesModule,
    SimulatorsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
