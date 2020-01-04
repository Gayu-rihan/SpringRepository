import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  constructor(private router: Router) { }
  isEdit = false;
  Employee: any = {};

  ngOnInit() {
  }
  addEmp() {
     var alertData=this.Employee.id + "   " + this.Employee.name + "   " + this.Employee.Salary +  "   " +this.Employee.Department;
    alert(alertData);
    if (this.isEdit == false) {
      this.Employee = {};
    }
  } 
  
}
