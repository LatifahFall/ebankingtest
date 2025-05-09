import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { ClientDashboardComponent } from './client-dashboard/client-dashboard.component';
import { EmployeeDashboardComponent } from './employee-dashboard/employee-dashboard.component';

export const routes: Routes = [
  { path: 'login', component: LoginComponent }, // Keep this for login page
  { path: 'admin-dashboard', component: AdminDashboardComponent },
  { path: 'client-dashboard', component: ClientDashboardComponent },
  { path: 'employee-dashboard', component: EmployeeDashboardComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' }, // Redirect to login as default
];
