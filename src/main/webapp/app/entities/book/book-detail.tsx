import React from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
// tslint:disable-next-line:no-unused-variable
import { Translate, ICrudGetAction, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './book.reducer';
import { IBook } from 'app/shared/model/book.model';
// tslint:disable-next-line:no-unused-variable
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IBookDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export class BookDetail extends React.Component<IBookDetailProps> {
  componentDidMount() {
    this.props.getEntity(this.props.match.params.id);
  }

  render() {
    const { bookEntity } = this.props;
    return (
      <Row>
        <Col md="8">
          <h2>
            <Translate contentKey="jhipsterdemo01App.book.detail.title">Book</Translate> [<b>{bookEntity.id}</b>]
          </h2>
          <dl className="jh-entity-details">
            <dt>
              <span id="bName">
                <Translate contentKey="jhipsterdemo01App.book.bName">B Name</Translate>
              </span>
            </dt>
            <dd>{bookEntity.bName}</dd>
            <dt>
              <span id="pulicationDate">
                <Translate contentKey="jhipsterdemo01App.book.pulicationDate">Pulication Date</Translate>
              </span>
            </dt>
            <dd>
              <TextFormat value={bookEntity.pulicationDate} type="date" format={APP_LOCAL_DATE_FORMAT} />
            </dd>
            <dt>
              <span id="price">
                <Translate contentKey="jhipsterdemo01App.book.price">Price</Translate>
              </span>
            </dt>
            <dd>{bookEntity.price}</dd>
            <dt>
              <Translate contentKey="jhipsterdemo01App.book.m">M</Translate>
            </dt>
            <dd>{bookEntity.mBName ? bookEntity.mBName : ''}</dd>
          </dl>
          <Button tag={Link} to="/entity/book" replace color="info">
            <FontAwesomeIcon icon="arrow-left" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.back">Back</Translate>
            </span>
          </Button>&nbsp;
          <Button tag={Link} to={`/entity/book/${bookEntity.id}/edit`} replace color="primary">
            <FontAwesomeIcon icon="pencil-alt" />{' '}
            <span className="d-none d-md-inline">
              <Translate contentKey="entity.action.edit">Edit</Translate>
            </span>
          </Button>
        </Col>
      </Row>
    );
  }
}

const mapStateToProps = ({ book }: IRootState) => ({
  bookEntity: book.entity
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(
  mapStateToProps,
  mapDispatchToProps
)(BookDetail);
