import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse } from '@angular/common/http';
import { catchError, filter, Observable, throwError } from 'rxjs';
import { Router } from '@angular/router';
import { drawerRoutes } from '../constantLibary';
import { ToasterService } from '../services/toaster.service';
import { TranslateService } from '@ngx-translate/core';

@Injectable()
export class ErrorInterceptor implements HttpInterceptor {
  NO_ERROR_TOASTER_ROUTES = ['/token'];
  constructor(
    private router: Router,
    private toasterService: ToasterService,
    private translate: TranslateService,
  ) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    return next.handle(request).pipe(
      filter((event) => event instanceof HttpResponse),
      catchError((response) => {
        this.handleErrorToaster(response);
        this.handleDrawerError(request);
        return throwError(() => new Error(response));
      }),
    );
  }

  handleErrorToaster(response: any) {
    if (!this.NO_ERROR_TOASTER_ROUTES.some((route) => response.url.includes(route))) {
      const errors = response.error.errors;
      errors.forEach((error: { errorKey: string; params: string[] }) => {
        const template = this.translate.instant('TRANSLATIONS.' + error.errorKey);
        const message = template.format(error.params);
        this.toasterService.showError(message);
      });
    }
  }

  handleDrawerError(request: HttpRequest<unknown>) {
    if (drawerRoutes.some((route) => request.url.includes(route))) {
      this.router.navigate(['']);
    }
  }
}
