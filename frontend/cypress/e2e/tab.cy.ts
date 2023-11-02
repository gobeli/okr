import * as users from '../fixtures/users.json';
import { onlyOn } from '@cypress/skip-test';

describe('Tab workflow tests', () => {
  beforeEach(() => {
    cy.loginAsUser(users.gl);
    onlyOn('chrome');
  });

  it('First tabbable element is help element', () => {
    cy.realPress('Tab');
    cy.focused().contains('Hilfe');
  });

  it('Tab forward and then tab backwards with tab+shift', () => {
    cy.tabForward();
    cy.tabForward();
    cy.tabForward();
    cy.tabForward();
    cy.focused().contains('GJ');
    cy.tabBackward();
    cy.tabBackward();
    cy.tabBackward();
    cy.focused().contains('Hilfe');
  });

  it.only('Open dialog via tab and enter', () => {
    cy.get('.objective').first().focus().contains('Wir wollen die Zusammenarbeit im Team steigern.');
    cy.tabForwardUntil('[data-testId="add-keyResult"]');
    const button = cy.focused();
    button.click();
    cy.tabForward();
    cy.get('.mat-mdc-dialog-content').contains('Key Result erfassen');
    cy.tabBackward();
    cy.focused().click();
    button.then((buttonBefore) => {
      console.log(buttonBefore.get(0));
      cy.focused().then((buttonAfter) => {
        console.log(buttonAfter.get(0));
        expect(buttonBefore.get(0)).to.eql(buttonAfter.get(0));
      });
    });
  });
});
