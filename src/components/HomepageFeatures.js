import React from 'react';
import clsx from 'clsx';
import styles from './HomepageFeatures.module.css';

const FeatureList = [
  {
    title: 'High levels of customisation',
    Svg: require('../../static/img/image1.svg').default,
    description: (
      <>
          While we have balanced the majority of the features there is a large level of customisation through configuration options. If you are feeling we are lacking an option, feel free to make a suggestion on the <a href={"https://discord.sekwah.com/"} target="_blank">discord</a>.
      </>
    ),
  },
  {
    title: 'Ability System',
    Svg: require('../../static/img/image2.svg').default,
    description: (
      <>
          A custom ability system which allows for the easy creation of abilities. While you cannot create your own abilities in game, this allows for easy integration with addons.
      </>
    ),
  },
  {
    title: 'Custom Animations (Coming Soon)',
    Svg: require('../../static/img/image3.svg').default,
    description: (
      <>
          We are currently in the process of remaking the animation system. For previews of what it used to look like check out the old 1.7.10 copies.
      </>
    ),
  },
];

function Feature({Svg, title, description}) {
  return (
    <div className={clsx('col col--4')}>
      <div className="text--center">
        <Svg className={styles.featureSvg} alt={title} />
      </div>
      <div className="text--center padding-horiz--md">
        <h3>{title}</h3>
        <p>{description}</p>
      </div>
    </div>
  );
}

export default function HomepageFeatures() {
  return (
    <section className={styles.features}>
      <div className="container">
        <div className="row">
          {FeatureList.map((props, idx) => (
            <Feature key={idx} {...props} />
          ))}
        </div>
      </div>
    </section>
  );
}
