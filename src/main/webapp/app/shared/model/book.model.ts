import { Moment } from 'moment';

export interface IBook {
  id?: number;
  bName?: string;
  pulicationDate?: Moment;
  price?: number;
  mBName?: string;
  mId?: number;
}

export const defaultValue: Readonly<IBook> = {};
