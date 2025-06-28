import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../services-acct/login.service';
import { InputComponent } from '../../components-gui/input/input.component';
import { PanelComponent } from '../../components-gui/panel/panel.component';
import { ButtonComponent } from '../../components-gui/button/button.component';
import { LabelComponent } from '../../components-gui/label/label.component';
import { Router } from '@angular/router';

const INITIAL_LABEL_MESSAGE : string = "Please enter your credentials to log in."
const ERROR_LABEL_MESSAGE   : string = "Unable to log in. Please try again."
const SUCCESS_LABEL_MESSAGE : string = "Login successful. Redirecting to main."

@Component({
  selector: 'app-login-form',
  imports: [
    InputComponent,
    PanelComponent,
    ButtonComponent,
    LabelComponent
  ],
  templateUrl: './login-form.component.html',
  styleUrl: './login-form.component.less'
})
export class LoginFormComponent implements OnInit {

  username : string = ""
  password : string = ""

  labelMessage = INITIAL_LABEL_MESSAGE

  error : boolean = false

  success : boolean = false

  constructor(
    private loginService : LoginService,
    private router: Router
  ) {

  }

  ngOnInit(): void {
    this.resetErrorSate()
  }

  onFormSubmit() : void {
    this.loginService.login(this.username, this.password).subscribe({
      next: () => {
        this.resetErrorSate()
        this.labelMessage = SUCCESS_LABEL_MESSAGE
        this.success = true
        this.router.navigate(['/main'])
      },
      error: () => {
        this.error = true
        this.labelMessage = ERROR_LABEL_MESSAGE
      }
    })
  }

  private resetErrorSate() : void {
    this.error = false
    this.success = false
    this.labelMessage = INITIAL_LABEL_MESSAGE
  }

  isError() : boolean {
    return this.error
  }

  isSuccess() : boolean {
    return this.success
  }

}
