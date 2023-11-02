import * as users from '../fixtures/users.json';

describe('OKR Login', () => {
  beforeEach(() => {
    cy.loginAsUser(users.gl);
  });

  it('Login and check correct name is displayed', () => {
    cy.title().should('equal', 'Puzzle OKR');
    cy.get("pzsh-menu-dropdown > div[slot='toggle']").contains(users.gl.name);
  });

  it('Login  and logout', () => {
    cy.title().should('equal', 'Puzzle OKR');
    cy.get("pzsh-menu-dropdown > div[slot='toggle']").click();
    cy.getByTestId('logout').click();
    cy.origin('https://idp-mock-okr.ocp-internal.cloudscale.puzzle.ch', () => {
      cy.url().should('include', 'https://idp-mock-okr.ocp-internal.cloudscale.puzzle.ch');
      cy.get('#kc-page-title').contains('Sign in to your account');
    });
  });
});
