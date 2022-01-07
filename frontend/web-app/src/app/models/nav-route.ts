export interface NavRoute {
  title: string;
  icon: string;
  display: string;
  route: string;
  children?: NavRoute;
}
