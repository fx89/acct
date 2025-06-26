import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../services-acct/login.service';

@Component({
  selector: 'app-login-form',
  imports: [],
  templateUrl: './login-form.component.html',
  styleUrl: './login-form.component.less'
})
export class LoginFormComponent implements OnInit {

  constructor( private loginService : LoginService ) {}

  ngOnInit(): void {
    this.loginService.login("admin", "admin").subscribe(
      {
        next(userAccessToken) {
          console.log("success")
          console.log(userAccessToken)
        },
        error(err) {
          console.log("error")
          console.log(err)
        }
      }
    )
  }



}
