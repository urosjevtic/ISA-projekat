import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {MatToolbar} from '@angular/material/toolbar';
import {MatButton, MatButtonModule, MatIconButton} from '@angular/material/button';
import {MatFormField, MatFormFieldModule, MatHint, MatLabel} from '@angular/material/form-field';
import {MatInput, MatInputModule} from '@angular/material/input';
import {MatTable, MatTableModule} from '@angular/material/table';
import {MatIcon, MatIconModule} from '@angular/material/icon';
import {MatRadioButton, MatRadioGroup, MatRadioModule} from '@angular/material/radio';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatSelect, MatSelectModule} from '@angular/material/select';
import { MatOption } from '@angular/material/core';
import {MatCheckbox, MatCheckboxModule} from '@angular/material/checkbox';
import {MatCard, MatCardModule} from '@angular/material/card';
import { MatToolbarModule } from '@angular/material/toolbar';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ReactiveFormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { JwtInterceptor } from '../auth/jwt/jwt.interceptor';


@NgModule({
  declarations: [],
  imports: [
    MatToolbarModule,
    CommonModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatTableModule,
    MatIconModule,
    MatRadioModule,
    MatSnackBarModule,
    MatSelectModule,
    MatCheckboxModule,
    MatCardModule,
    BrowserAnimationsModule,
    ReactiveFormsModule

  ],
  exports: [
    MatToolbar,
    MatButton,
    MatFormField,
    MatLabel,
    MatInput,
    MatTable,
    MatIconButton,
    MatIcon,
    MatRadioButton,
    MatRadioGroup,
    MatSelect,
    MatOption,
    MatCheckbox,
    MatCardModule,
    BrowserAnimationsModule,
    ReactiveFormsModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true,
    }]
})
export class MaterialModule { }
