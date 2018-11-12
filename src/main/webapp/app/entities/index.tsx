import React from 'react';
import { Switch } from 'react-router-dom';

// tslint:disable-next-line:no-unused-variable
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ExTransport from './ex-transport';
import Author from './author';
import Book from './book';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}/ex-transport`} component={ExTransport} />
      <ErrorBoundaryRoute path={`${match.url}/author`} component={Author} />
      <ErrorBoundaryRoute path={`${match.url}/book`} component={Book} />
      {/* jhipster-needle-add-route-path - JHipster will routes here */}
    </Switch>
  </div>
);

export default Routes;
