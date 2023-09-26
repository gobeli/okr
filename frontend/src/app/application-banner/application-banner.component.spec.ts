import { ComponentFixture, fakeAsync, TestBed, tick } from '@angular/core/testing';

import { ApplicationBannerComponent } from './application-banner.component';
import { By } from '@angular/platform-browser';

class ResizeObserverMock {
  observe() {}
  unobserve() {}
  disconnect() {}
}

describe('ApplicationBannerComponent', () => {
  let component: ApplicationBannerComponent;
  let fixture: ComponentFixture<ApplicationBannerComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ApplicationBannerComponent],
    });
    fixture = TestBed.createComponent(ApplicationBannerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should set banner position to fixed when scrolled more than the height of the banner', fakeAsync(() => {
    //Set bannerHeight bigger than the default banner
    let bannerHeight: number = 200;
    //Scroll more than the banner height
    let scrollTop: number = bannerHeight + 20;

    //Set banner style
    component.setOKRBannerStyle(bannerHeight, scrollTop);
    tick(200);

    //Assert that position was changed
    fixture.detectChanges();
    expect(fixture.debugElement.query(By.css('#okrBanner')).attributes['style']).toContain('position: fixed');
  }));

  it('should set banner position to relative if user is at top of page', fakeAsync(() => {
    //Set bannerHeight to default height
    let bannerHeight: number = 160;
    //Set user to top of page
    let scrollTop: number = 0;

    //Set banner style
    component.setOKRBannerStyle(bannerHeight, scrollTop);
    tick(200);

    //Assert that position was changed
    fixture.detectChanges();
    expect(fixture.debugElement.query(By.css('#okrBanner')).attributes['style']).toContain('position: relative');
  }));

  it('should should hide banner if scrolled down', fakeAsync(() => {
    //Set bannerHeight to default
    let bannerHeight: number = 160;
    //Scroll more than the height of the banner
    let scrollTop: number = 180;
    //Set lastScrollPosition to smaller than scrollTop => user scrolls down
    component.lastScrollPosition = 160;

    //Set banner style
    component.setOKRBannerStyle(bannerHeight, scrollTop);
    tick(200);

    //Assert that banner is hidden was changed
    fixture.detectChanges();
    expect(fixture.debugElement.query(By.css('#okrBanner')).attributes['style']).toContain(
      'top: -' + (component.PUZZLE_TOP_BAR_HEIGHT + bannerHeight),
    );
  }));

  it('should show banner if scrolled up', fakeAsync(() => {
    //Set bannerHeight to default
    let bannerHeight: number = 160;
    //Scroll more than the height of the banner
    let scrollTop: number = 180;
    //Set lastScrollPosition to bigger than scrollTop => user scrolls up
    component.lastScrollPosition = 200;

    //Set banner style
    component.setOKRBannerStyle(bannerHeight, scrollTop);
    tick(200);

    //Assert that banner is visible
    fixture.detectChanges();
    expect(fixture.debugElement.query(By.css('#okrBanner')).attributes['style']).toContain(
      'top: ' + component.PUZZLE_TOP_BAR_HEIGHT,
    );
  }));

  it('should call setOKRBannerStyle() when changing header appearance', () => {
    jest.spyOn(component, 'setOKRBannerStyle').mockReturnValue();

    //Set bannerHeight to default and execute header appearance change
    let bannerHeight: number = 160;
    component.changeHeaderAppearance(bannerHeight);

    //Assert that banner is visible
    fixture.detectChanges();
    expect(component.setOKRBannerStyle).toHaveBeenCalled();
  });

  it('should call removeScrollEventListener() when updating scroll event listeners', () => {
    jest.spyOn(component, 'removeScrollEventListener').mockReturnValue();

    //Set bannerHeight to default and execute updating of scroll event listeners
    let bannerHeight: number = 160;
    component.updateScrollEventListeners(bannerHeight);

    //Assert that banner is visible
    fixture.detectChanges();
    expect(component.removeScrollEventListener).toHaveBeenCalled();
  });
});
