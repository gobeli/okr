import { ChangeDetectionStrategy, Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router } from '@angular/router';
import { filter, map, Observable, Subscription } from 'rxjs';
import { TranslateService } from '@ngx-translate/core';
import { OAuthService } from 'angular-oauth2-oidc';
import { ConfigService } from './config.service';
import { RouteService } from './shared/services/route.service';
import { NotifierService } from './shared/services/notifier.service';
import { ObjectiveService } from './shared/services/objective.service';
import { Objective } from './shared/types/model/Objective';
import { ObjectiveMin } from './shared/types/model/ObjectiveMin';
import { KeyresultMin } from './shared/types/model/KeyresultMin';
import { SidenavModel } from './shared/types/model/SidenavModel';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class AppComponent implements OnInit, OnDestroy {
  currentUrl: string = '/';
  isEnvStaging$: Observable<boolean>;
  drawerOpen: boolean = false;
  sidenavContentInformation!: { id: string; type: string };

  constructor(
    public router: Router,
    private translate: TranslateService,
    private routeService: RouteService,
    private oauthService: OAuthService,
    private configService: ConfigService,
    private notifierService: NotifierService,
  ) {
    translate.setDefaultLang('de');
    translate.use('de');

    // Try to login via url state
    oauthService.loadDiscoveryDocumentAndTryLogin().then(() => {
      // if the login failed initialize code flow
      if (!oauthService.hasValidAccessToken()) {
        this.oauthService.initCodeFlow();
      }
      oauthService.setupAutomaticSilentRefresh();
      location.hash = '';
    });
    this.isEnvStaging$ = this.configService.config$.pipe(
      map((config) => {
        return config.activeProfile === 'staging';
      }),
    );
  }

  openObjectiveDetail() {
    this.drawerOpen = true;
    this.disableScrolling();
  }

  ngOnInit(): void {
    this.router.events
      .pipe(
        filter((event) => event instanceof NavigationEnd),
        map((event) => event as NavigationEnd),
      )
      .subscribe((event) => {
        this.currentUrl = event.url;
        if (this.currentUrl.startsWith('/objective/')) {
          this.openObjectiveDetail();
          const objectiveId = this.currentUrl.split('/')[2];
          this.sidenavContentInformation = { id: objectiveId, type: 'objective' };
        }
      });

    this.notifierService.closeDetailSubject.subscribe(() => {
      this.closeDrawer();
    });
  }

  ngOnDestroy() {}

  isOverview(): null | true {
    return this.convertFalseToNull(!this.isTeam());
  }

  isTeam(): null | true {
    return this.convertFalseToNull(this.currentUrl.startsWith('/team'));
  }

  /**
   * Puzzle Shell use `active="null"` instead of `active="false"`!
   */
  convertFalseToNull(value: boolean): true | null {
    return value ? true : null;
  }

  /**
   * Disable Puzzle Shell link handling.
   */
  navigate(location: string): boolean {
    this.routeService.navigate(location);
    return false;
  }

  private disableScrolling() {
    document.getElementById('drawer-container')?.setAttribute('style', 'z-index: 100;');
    document.body.setAttribute('style', 'overflow: hidden;');
  }
  enableScrolling() {
    document.getElementById('drawer-container')?.setAttribute('style', 'z-index: 99;');
    document.body.setAttribute('style', 'overflow: visible;');
  }

  closeDrawer() {
    this.drawerOpen = false;
    this.router.navigate(['/']);
  }

  login() {}

  protected readonly close = close;
}
